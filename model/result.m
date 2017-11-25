% [num] = textread('result1.txt');
% standard = linspace(1,1,length(num));
% plot(num(:,1)./20,'b.-')
% hold on
% plot(num(:,2),'r.-')
% plot(standard,'k--')
% xlabel('k的大小')
% ylabel('归一化后的矩阵该项的值')
% title('矩阵近似程度随k的变化情况')
% legend('中国/ns - docs1','一九九七年/t - docs1','标准线')
% box on
% saveas(gcf,'matrix-k!0.png')

%zero
[num] = textread('result2.txt');
standard = linspace(0,0,length(num));
plot(num(:,1),'b.-')
hold on
plot(num(:,2),'r.-')
plot(standard,'k--')
xlabel('k的大小')
ylabel('矩阵该项的值')
title('矩阵近似程度随k的变化情况')
legend('蝴蝶/n - docs1','早已/d - docs2','标准线')
box on
saveas(gcf,'matrix-kis0.png')